import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-message',
  template: `
  <div class="ui-message">
    <p-message *ngIf="temErro()" severity="error" text="{{text}}"></p-message>
  </div>
  `,
  styles: [`
    .ui-message {
      margin-top: 4px;
    }
  `
  ]
})
export class MessageComponent implements OnInit {

  @Input() error: string;
  @Input() control: FormControl;
  @Input() text: string;

  constructor() { }

  ngOnInit(): void {
  }

  public temErro(): boolean {
    return this.control.hasError(this.error) && this.control.dirty;
  }

}
